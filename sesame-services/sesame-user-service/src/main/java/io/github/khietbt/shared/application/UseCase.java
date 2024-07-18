package io.github.khietbt.shared.application;

public interface UseCase<Request, Response> {
    Response execute(Request request);
}
