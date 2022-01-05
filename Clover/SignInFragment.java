private void signIn() {
    mSignIn.setClickable(false);
    mCircle.setVisibility(View.VISIBLE);

    DatabaseModel.signIn(email, password).addOnCompleteListener(new OnCompleteListener() {
        @Override
        public void onComplete(@NonNull Task task) {
            if (task.isSuccessful()) {
                DatabaseModel.getCurrentUser();
                getActivity().finishAffinity();
                getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
                Reuse.startActivity(getActivity());
            } else {
                errorDialog();
            }
        }
    });
}