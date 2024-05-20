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

package ro.sorin.pynt.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;



public class TodoEntry implements Comparable<TodoEntry> {

    private UUID id;
    private String title;
    private String description;
    private boolean done;

    @JsonIgnore
    private final long createdAt;

    public TodoEntry() {
        this.id = UUID.randomUUID();
        this.createdAt = System.currentTimeMillis();
    }

    public TodoEntry(String title, String description) {
        this(UUID.randomUUID(), title, description);
    }

    public TodoEntry(UUID id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = System.currentTimeMillis();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isDone() {
        return done;
    }

    @Override
    public int compareTo(TodoEntry o) {
        return Long.compare(createdAt, o.createdAt);

    }
}
