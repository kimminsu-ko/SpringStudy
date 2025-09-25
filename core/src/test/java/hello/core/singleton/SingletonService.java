package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }
    //private 으로 다른곳에서 new 생성 막음
    private SingletonService() {

    }

    public void logic(){
        System.out.println("logic");
    }

    public static void main(String[] args) {
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();
    }
}
