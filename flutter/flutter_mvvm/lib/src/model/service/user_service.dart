import 'package:flutter_mvvm/src/model/user.dart';
import '../repository/user_repository.dart';

class UserService {
  final UserRepository _userRepository = UserRepository();

  Future<User> getUser() async {
    return await _userRepository.getUser();
  }
}
