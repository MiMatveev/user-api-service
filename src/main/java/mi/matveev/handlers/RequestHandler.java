package mi.matveev.handlers;

import org.springframework.http.ResponseEntity;

public interface RequestHandler<T> {
    ResponseEntity<?> process (T requestBody);
}
