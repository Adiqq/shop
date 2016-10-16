package Infrastructure;

/**
 * Created by Adiq on 16.10.2016.
 */
public class Response<T> {
    private Result result;
    private T content;

    public Response(Result result, T content) {
        this.result = result;
        this.content = content;
    }

    public Result getResult() {
        return result;
    }

    public T getContent() {
        return content;
    }
}
