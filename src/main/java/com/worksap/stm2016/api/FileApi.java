package com.worksap.stm2016.api;

import com.worksap.stm2016.api.util.JsonResponse;
import com.worksap.stm2016.api.util.JsonResponse.ResponseStatus;
import com.worksap.stm2016.domain.FileProfile;
import com.worksap.stm2016.domain.User;
import com.worksap.stm2016.repository.FileProfileRepository;
import com.worksap.stm2016.service.FileService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import static com.worksap.stm2016.specification.BasicSpecs.SortAndFilter;
import static com.worksap.stm2016.specification.BasicSpecs.isValue;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/file")
public class FileApi {

    private static final Logger logger = LoggerFactory.getLogger(FileApi.class);

    @Autowired
    FileService fileService;

    @RequestMapping(method = RequestMethod.GET)
    public JSONObject getList(@RequestParam(name = "sort") String sort,
                              @RequestParam(name = "order") String order,
                              @RequestParam(name = "limit") Integer limit,
                              @RequestParam(name = "offset") Integer offset,
                              @RequestParam(name = "filter", required = false) String filter) throws ParseException {

        return fileService.getList( sort, order, limit, offset, filter);
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
                fileService.save(fileProfile);

                return new JsonResponse(ResponseStatus.OK, "File uploaded");
            }
        } else {
            if (overwrite == true) {
                absPath.getParentFile().mkdirs();

                BufferedOutputStream stream = null;
                stream = new BufferedOutputStream(new FileOutputStream(absPath));
                stream.write(file.getBytes());
                stream.close();

                FileProfile fileProfile = fileService.getByPath(absPath.toString());
                fileProfile.setName(name);
                fileProfile.setInfo(info);
                fileService.save(fileProfile);
                return new JsonResponse(ResponseStatus.OK, "File overwritten");
            } else {
                return null;
            }
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public JsonResponse delete(@PathVariable("id") Long id){
        if (fileService.delete(id)) {
            return new JsonResponse(ResponseStatus.OK, "File deleted");
        } else {
            return new JsonResponse(ResponseStatus.ERROR, "File can't be deleted. Close the file window and try again.");
        }
    }
}
