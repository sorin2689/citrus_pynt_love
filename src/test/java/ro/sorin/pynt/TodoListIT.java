package ro.sorin.pynt;

import org.citrusframework.annotations.CitrusTest;
import org.citrusframework.http.client.HttpClient;
import org.citrusframework.message.MessageType;
import org.citrusframework.testng.spring.TestNGCitrusSpringSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.testng.annotations.Test;

import static org.citrusframework.dsl.XpathSupport.xpath;
import static org.citrusframework.http.actions.HttpActionBuilder.http;
import static org.citrusframework.validation.json.JsonPathMessageValidationContext.Builder.jsonPath;

public class TodoListIT extends TestNGCitrusSpringSupport {


    @Autowired
    private HttpClient httpClient;

    @Test
    @CitrusTest
    public void testGet() {
        $(http()
                .client(httpClient)
                .send()
                .get("/api/todolist")
                .message()
                .accept(MediaType.APPLICATION_JSON.toString()));

        $(http()
                .client(httpClient)
                .receive()
                .response(HttpStatus.OK));
    }



    @Test
    @CitrusTest
    public void testTodoLifecycle() {
        variable("todoId", "citrus:randomUUID()");
        variable("todoName", "citrus:concat('todo_', citrus:randomNumber(4))");
        variable("todoDescription", "Description: ${todoName}");

        //Create
        $(http().client(httpClient)
                .send()
                .post("/api/todolist")
                .message()
                .type(MessageType.JSON)
                .contentType("application/json")
                .body("{ \"id\": \"${todoId}\", " +
                        "\"title\": \"${todoName}\", " +
                        "\"description\": \"${todoDescription}\", " +
                        "\"done\": false}"));

        $(http().client(httpClient)
                .receive()
                .response(HttpStatus.OK)
                .message()
                .type(MessageType.PLAINTEXT)
                .body("${todoId}"));

        //Verify existence
        $(http().client(httpClient)
                .send()
                .get("/api/todo/${todoId}")
                .message()
                .accept("application/json"));

        $(http().client(httpClient)
                .receive()
                .response(HttpStatus.OK)
                .message()
                .type(MessageType.JSON)
                .validate(jsonPath()
                        .expression("$.id", "${todoId}")
                        .expression("$.title", "${todoName}")
                        .expression("$.description", "${todoDescription}")
                        .expression("$.done", false)));

        //Delete
        $(http().client(httpClient)
                .send()
                .delete("/api/todo/${todoId}")
                .message()
                .accept("application/json"));

        $(http().client(httpClient)
                .receive()
                .response(HttpStatus.OK));
    }

}