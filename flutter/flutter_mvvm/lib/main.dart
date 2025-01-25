import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_mvvm/src/model/repository/user_repository.dart';
import 'package:flutter_mvvm/src/view/Home.dart';
import 'package:flutter_mvvm/src/viewmodel/user_view_model.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(
    MultiProvider(
      providers: [
        Provider(create: (context) => UserRepository()),
        ChangeNotifierProvider(create: (context) => UserViewModel()),
      ],
      child: const MyApp(),
    ),
  );
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      title: 'mvvm example',
      home: HomeView(),
    );
  }
}