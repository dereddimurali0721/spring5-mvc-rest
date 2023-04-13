package guru.springfamework.ExceptionHandler;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(){

    }

    ResourceNotFound(String exception){
        super(exception);
    }

    ResourceNotFound(String exception,Throwable throwable){
        super(exception,throwable);
    }

    ResourceNotFound(Throwable throwable){
        super(throwable);
    }

    ResourceNotFound(String exception,Throwable throwable,boolean enableSuppression,boolean wariableStackTrace){
        super(exception,throwable,enableSuppression,wariableStackTrace);
    }
}
