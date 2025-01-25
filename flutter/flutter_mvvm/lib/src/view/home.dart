import 'package:flutter/material.dart';
import 'package:flutter_mvvm/src/viewmodel/user_view_model.dart';
import 'package:provider/provider.dart';

import '../model/user.dart';

class HomeView extends StatefulWidget {
  const HomeView({super.key});

  @override
  State<HomeView> createState() => _HomeViewState();
}

class _HomeViewState extends State<HomeView> {
  late final UserViewModel userViewModel = UserViewModel();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text('mvvm example'),
        ),
        body: Center(
          child: Consumer<UserViewModel>(
            builder: (context, userViewModel, child) {
              if (userViewModel.user != null) {
                User user = userViewModel.user!;
                return Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    Text(
                      "Name is ${user.firstName} ${user.lastName}\n"
                      "Gender is ${user.gender}\n"
                      "Email is ${user.email}\n",
                      style: const TextStyle(fontSize: 20),
                    ),
                    SizedBox(
                      height: 20,
                    ),
                    ElevatedButton(
                        onPressed: () async {
                          await userViewModel.fetchUser();
                        },
                        child: const Text('fetch user'))
                  ],
                );
              } else {
                return ElevatedButton(
                  onPressed: () async {
                    await userViewModel.fetchUser();
                  },
                  child: const Text('fetch user'),
                );
              }
            },
          ),
        ));
  }
}
