package com.worksap.stm2016.api.message;


import com.worksap.stm2016.api.util.JsonArrayResponse;
import com.worksap.stm2016.domain.message.Notification;
import com.worksap.stm2016.service.message.NotificationService;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Shuang on 4/25/2016.
 */
@RestController
@RequestMapping("/api/notification")
public class NotificationApi {

    private static final Logger logger = LoggerFactory.getLogger(NotificationApi.class);

    @Autowired
    NotificationService notificationService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Notification get(@PathVariable("id") Long id) {
        return notificationService.get(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public JsonArrayResponse getList(@RequestParam(name = "sort") String sort,
                                     @RequestParam(name = "order") String order,
                                     @RequestParam(name = "limit") Integer limit,
                                     @RequestParam(name = "offset") Integer offset,
                                     @RequestParam(name = "filter", required = false) String filter) throws ParseException {

        return notificationService.getList(sort, order, limit, offset, filter);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Notification update (@RequestBody Notification notification) {
        return notificationService.update(notification);
    }
}
