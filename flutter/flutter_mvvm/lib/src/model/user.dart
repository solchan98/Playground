class User {
  final String gender;
  final String firstName;
  final String lastName;
  final String email;

  const User({
    required this.gender,
    required this.firstName,
    required this.lastName,
    required this.email,
  });

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      gender: json["gender"],
      firstName: json["name"]["first"],
      lastName: json["name"]["last"],
      email: json["email"],
    );
  }
}
