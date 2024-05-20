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

package ro.sorin.pynt.dao;

import ro.sorin.pynt.model.TodoEntry;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;


/**
 * @author Christoph Deppisch
 */
public class InMemoryTodoListDao implements TodoListDao {

    /** In memory storage */
    private final SortedSet<TodoEntry> storage = new ConcurrentSkipListSet<>();

    @Override
    public void save(TodoEntry entry) {
        storage.add(entry);
    }

    @Override
    public Set<TodoEntry> list() {
        return Collections.unmodifiableSet(storage);
    }

    @Override
    public Set<TodoEntry> list(int limit) {
        return storage.stream().limit(limit).collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public void delete(TodoEntry entry) {
        storage.removeIf(todo -> todo.getId().equals(entry.getId()));
    }

    @Override
    public void deleteAll() {
        storage.clear();
    }

    @Override
    public void update(TodoEntry entry) {
        Optional<TodoEntry> found = storage.stream()
                .filter(current -> current.getId().equals(entry.getId()))
                .findFirst();

        if (found.isEmpty()) {
            throw new RuntimeException(String.format("Unable to find entry with uuid '%s'", entry.getId()));
        }

        found.get().setTitle(entry.getTitle());
        found.get().setDescription(entry.getDescription());
        found.get().setDone(entry.isDone());
    }
}
