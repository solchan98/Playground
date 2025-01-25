import 'package:dio/dio.dart';

import '../user.dart';

class UserRepository {
  final String url = 'https://randomuser.me/api/';

  getUser() async {
    final result = await Dio().get(url);
    Map<String, dynamic> jsonData = result.data['results'][0];
    return User.fromJson(jsonData);
  }
}
