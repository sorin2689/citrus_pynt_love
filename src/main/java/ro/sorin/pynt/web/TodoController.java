/*
 * Copyright 2006-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ro.sorin.pynt.web;

import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ro.sorin.pynt.model.TodoEntry;
import ro.sorin.pynt.service.TodoListService;

/**
 * @author Christoph Deppisch
 */
@Controller
@RequestMapping("/api/todo")
public class TodoController {

    @Autowired
    private TodoListService todoListService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public TodoEntry getEntry(@PathVariable(required = true, value = "id") UUID id) {
        return todoListService.getEntry(id);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void setEntryStatus(@PathVariable(required = true, value = "id") UUID id,
                               @RequestParam(value = "done") boolean done) {
        todoListService.setStatus(id, done);
    }


    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteEntryByTitle(@RequestParam(value = "title") String title) {
        todoListService.deleteEntry(title);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteEntry(@PathVariable(value = "id") UUID id) {
        todoListService.deleteEntry(id);
    }
}
