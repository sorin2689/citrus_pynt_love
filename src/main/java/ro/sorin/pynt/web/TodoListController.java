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

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ro.sorin.pynt.model.TodoEntry;
import ro.sorin.pynt.service.TodoListService;

/**
 * @author Christoph Deppisch
 */
@Controller
@RequestMapping("/api/todolist")
public class TodoListController {

    @Autowired
    private TodoListService todoListService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Set<TodoEntry> list() {
        return todoListService.getAllEntries();
    }

    @RequestMapping(value = "/{limit}", method = RequestMethod.GET)
    @ResponseBody
    public Set<TodoEntry> listWithLimit(@PathVariable(value = "limit") final int limit) {
        return todoListService.getAllEntries(limit);
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestBody TodoEntry entry) {
        todoListService.addEntry(entry);
        return entry.getId().toString();
    }


    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void clear() {
        todoListService.clear();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public Integer getTodoCount() {
        return todoListService.getAllEntries().size();
    }

}
