package io.joo.tdd.treason.user;

public class UserRegister {
    private WeakPasswordChecker passwordChecker;
    private UserRepository userRepository;
    private EmailNotifier emailNotifier;

    public UserRegister(WeakPasswordChecker passwordChecker,UserRepository userRepository) {
        this.passwordChecker = passwordChecker;
        this.userRepository = userRepository;
        this.emailNotifier = emailNotifier;
    }

    public void register(String id, String password, String email) {
        //비밀번호 검증: 먼저 passwordChecker 객체를 사용하여 입력된 비밀번호(pw)가 약한지 체크합니다. 만약 비밀번호가 약하다고 판단되면 WeakPasswordException 예외를 발생시킵니다.
        //아이디 중복 확인: userRepository 객체를 사용하여 주어진 아이디(id)를 가진 사용자가 이미 존재하는지 확인합니다. 만약 해당 아이디를 가진 사용자가 이미 존재하면 DupIdException 예외를 발생시킵니다.
        //사용자 저장: 위 두 가지 조건을 통과하면 새로운 User 객체를 생성하고, userRepository에 저장합니다.
        //등록 이메일 전송: 마지막으로 emailNotifier 객체를 사용하여 등록된 이메일 주소(email)로 환영 메일을 전송합니다.

        if (passwordChecker.checkPasswordWeak(password)) {
            throw new WeakPasswordException();
        }
        User user = userRepository.findById(id);
        if (user != null) {
            throw new DupIdException();
        }
        userRepository.save(new User(id, password, email));
//        emailNotifier.sendRegisterEmail(email);
    }
}
