// file SignInFragment.java

import com.google.firebase.auth.FirebaseAuth; // import thư viện Firebase Authentication

/*
Phương thức dùng để đăng nhập sử dụng Firebase Authentication,
phương thức này nằm trong phương thức setOnClickListener và
được gọi khi người dùng nhấn vào button đăng nhập

PARAMS:
    email: là email của người dùng, lấy từ view.
    password: là password của người dùng, lấy từ view.
*/

private void signIn(String email, String password) {
    FirebaseAuth.getInstance()
        .signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
                    /* nếu Firebase Authentication xác nhận người dùng này
                    này có tồn tại và đăng nhập thành công, đi đến màn hình chính*/
                } else {
                    /*Thất bại do sai password, email không tồn tại trong Firebase
                    Authentication => hiển thị Alert Dialog báo lỗi */
                }
            }
    });
}