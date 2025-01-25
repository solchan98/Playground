import 'package:flutter/foundation.dart';
import 'package:flutter_mvvm/src/model/service/user_service.dart';

import '../model/user.dart';

class UserViewModel with ChangeNotifier {

  final UserService _userService = UserService();

  User? user;

  Future<void> fetchUser() async {
    user = await _userService.getUser();
    notifyListeners();
  }
}