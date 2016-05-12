package com.worksap.stm2016.api;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.worksap.stm2016.api.util.JsonResponse;
import com.worksap.stm2016.api.util.JsonResponse.ResponseStatus;
import com.worksap.stm2016.domain.FileProfile;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.enums.UserStatus;
import com.worksap.stm2016.form.UserRegisterForm;
import com.worksap.stm2016.repository.FileProfileRepository;
import com.worksap.stm2016.repository.UserRepository;
import com.worksap.stm2016.service.user.UserService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import static com.worksap.stm2016.specification.BasicSpecs.*;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/file")
public class FileApi {

    private static final Logger logger = LoggerFactory.getLogger(FileApi.class);

    @Autowired
    FileProfileRepository fileProfileRepository;

    @RequestMapping(method = RequestMethod.GET)
    public JSONObject getFileList(@RequestParam(name = "sort") String sort,
                                    @RequestParam(name = "order") String order,
                                    @RequestParam(name = "limit") Integer limit,
                                    @RequestParam(name = "offset") Integer offset,
                                    @RequestParam(name = "filter", required = false) String filter) throws ParseException {

        ArrayList<Specification> specs = new ArrayList<>();

        if (filter != null) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(filter);
            JSONObject filterObj = (JSONObject) obj;
            for (Iterator iterator = filterObj.keySet().iterator(); iterator.hasNext(); ) {
                String key = (String) iterator.next();
                String search = (String) filterObj.get(key);
                Specification spec;
                //if (key.equals("user")) {
                    spec = isValue(key, search);
                //}
                specs.add(spec);
            }
        }

        JSONObject result = SortAndFilter( sort,  order,  limit,  offset,  filter,  specs, fileProfileRepository);
        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    public JsonResponse upload(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "type") String type,
            @RequestParam(value = "info", required = false) String info,
            @RequestParam(value = "user", required = false) User user,
            @RequestParam(value = "overwrite", required = false) Boolean overwrite) throws IOException {

        // Get the filename and build the local file path
        String filename = file.getOriginalFilename();
        String directory = "/files/";
        if (user != null) {
            directory += "user/" + user.getId().toString();
        } else {
            directory += type;
        }

        String relPath = Paths.get(directory, filename).toString();

        // Save the file locally
        File absPath = new File(new File(".").getAbsolutePath() + relPath);
        if (overwrite == null) {
            if (absPath.exists()) {
                return new JsonResponse(ResponseStatus.ERROR, "This is a duplicate file name.  Would you like to override the existing file?  If no, please rename this file and re-upload.");
            } else {
                absPath.getParentFile().mkdirs();

                BufferedOutputStream stream = null;
                stream = new BufferedOutputStream(new FileOutputStream(absPath));
                stream.write(file.getBytes());
                stream.close();

                FileProfile fileProfile = new FileProfile();
                fileProfile.setPath(absPath.toString());
                fileProfile.setName(name);
                fileProfile.setUser(user);
                fileProfile.setType(type);
                fileProfile.setInfo(info);
                fileProfileRepository.save(fileProfile);

                return new JsonResponse(ResponseStatus.OK, "File uploaded");
            }
        } else {
            if (overwrite == true) {
                absPath.getParentFile().mkdirs();

                BufferedOutputStream stream = null;
                stream = new BufferedOutputStream(new FileOutputStream(absPath));
                stream.write(file.getBytes());
                stream.close();

                FileProfile fileProfile = fileProfileRepository.findOneByPath(absPath.toString());
                fileProfile.setName(name);
                fileProfile.setInfo(info);
                fileProfileRepository.save(fileProfile);
                return new JsonResponse(ResponseStatus.OK, "File overwritten");
            } else {
                return null;
            }
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public JsonResponse delete(@PathVariable("id") Long id){
        //Long id = Long.parseLong(String.valueOf(obj.get("id")));
        String path = fileProfileRepository.findOne(id).getPath();
        File file = new File(path);
        if (file.delete()) {
            fileProfileRepository.delete(id);
            return new JsonResponse(ResponseStatus.OK, "File deleted");
        } else {
            return new JsonResponse(ResponseStatus.ERROR, "File can't be deleted. Close the file window and try again.");
        }
    }
}
