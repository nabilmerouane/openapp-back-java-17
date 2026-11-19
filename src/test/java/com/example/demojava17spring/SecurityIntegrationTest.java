package com.example.demojava17spring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SecurityIntegrationTest {

  private final HttpClient httpClient = HttpClient.newHttpClient();
  private static final Pattern TOKEN_PATTERN = Pattern.compile("\"token\":\"([^\"]+)\"");

  @LocalServerPort private int port;

  @Test
  void userCanReadButCannotCreatePersonne() throws Exception {
    String token = login("user", "user123");

    assertStatus(send("GET", "/api/personnes", token, null), 200);
    assertStatus(
        send("POST", "/api/personnes", token, "{\"nom\":\"Test\",\"prenom\":\"User\"}"), 403);
  }

  @Test
  void adminCanCreatePersonne() throws Exception {
    String token = login("admin", "admin123");

    assertStatus(
        send("POST", "/api/personnes", token, "{\"nom\":\"Test\",\"prenom\":\"Admin\"}"), 200);
  }

  @Test
  void invalidTokenIsRejected() throws Exception {
    assertStatus(send("GET", "/api/personnes", "not-a-valid-token", null), 401);
  }

  private String login(String username, String password) throws Exception {
    HttpResponse<String> result =
        send(
            "POST",
            "/api/auth/login",
            null,
            "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}");
    assertStatus(result, 200);
    Matcher tokenMatcher = TOKEN_PATTERN.matcher(result.body());
    assertTrue(tokenMatcher.find(), "Login response should contain a JWT");
    return tokenMatcher.group(1);
  }

  private HttpResponse<String> send(String method, String path, String token, String body)
      throws Exception {
    HttpRequest.Builder request =
        HttpRequest.newBuilder(URI.create("http://localhost:" + port + path));
    if (token != null) {
      request.header("Authorization", "Bearer " + token);
    }
    if (body != null) {
      request
          .header("Content-Type", "application/json")
          .method(method, HttpRequest.BodyPublishers.ofString(body));
    } else {
      request.method(method, HttpRequest.BodyPublishers.noBody());
    }
    return httpClient.send(request.build(), HttpResponse.BodyHandlers.ofString());
  }

  private void assertStatus(HttpResponse<String> response, int expectedStatus) {
    assertEquals(expectedStatus, response.statusCode(), response.body());
  }
}
