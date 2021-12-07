import bcrypt from "bcrypt";
import userModel from "../models/userModel";

export const getLogin = (req, res) => {
  return res.render("login", { pageTitle: "Login" });
};
export const postLogin = async (req, res) => {
  const { ID, password } = req.body;

  const user = await userModel.findOne({ ID });
  if (!user) {
    req.flash("error", "An account with this ID does not exists");
    return res.status(400).send({"msg" : `id : ${req.body.ID}`});
  }
  const checkPW = await bcrypt.compare(password, user.password);
  if (!checkPW) {
    req.flash("error", "Wrong Password");
    return res.status(400).send({"msg" : "Wrong Password"});
  }
  user.userState = 1;
  await user.save();
  req.session.loggedIn = true;
  req.session.user = user;
  return res.send(user);
};

export const getJoin = (req, res) => {
  return res.render("join", { pageTitle: "Join" });
};
export const postJoin = async (req, res) => {
  const {
    ID,
    password,
    password_confirm,
    email,
    nickname,
    studentID,
    sex,
    address,
  } = req.body;

  const checkExistedID = await userModel.exists({ ID });
  if (checkExistedID) {
    return res.status(400).send({"msg" : "This ID is already taken"});
  }
  const checkExistedEmail = await userModel.exists({ email });
  if (checkExistedEmail) {
    return res.status(400).send({"msg" : "This email is already taken"});
  }
  const checkExistedNickname = await userModel.exists({ nickname });
  if (checkExistedNickname) {
    return res.status(400).send({"msg" : "This nickname is already taken"});
  }
  if (password !== password_confirm) {
    return res.status(400).send({"msg" : "Password confirmation does not match."});
  }
  /*if (!email.includes("@ajou.ac.kr")) {    
    return res.status(400).send({"msg" : "Domain of Email must be @ajou.ac.kr"});
  }*/
  try {
    await userModel.create({
      ID,
      password,
      email,
      nickname,
      studentID,
      sex,
      address,
      taste: [0, 0, 0, 0, 0, 0, 0, 0, 0],
      userState: 0,
      rating : 0
    });
  } catch (error) {
    return res.status(400).send({"msg" : error});
  }
  return res.send({"msg" : "OK"});
};
let tasteRank = [];
const handleRank = (index) => {
  switch (index) {
    case 0:
      tasteRank.push("한식");
      break;
    case 1:
      tasteRank.push("중식");
      break;
    case 2:
      tasteRank.push("일식");
      break;
    case 3:
      tasteRank.push("양식");
      break;
    case 4:
      tasteRank.push("분식");
      break;
    case 5:
      tasteRank.push("치킨");
      break;
    case 6:
      tasteRank.push("아시안");
      break;
    case 7:
      tasteRank.push("고깃집");
      break;
    case 8:
      tasteRank.push("술집");
      break;
    default:
  }
};
const checkTaste = (taste) => {
  tasteRank = [];
  let rank = 5;
  for (let i = 0; i < 9; i++) { 
    if (rank == 0) {
      break;
    }
    if (taste[i] == rank) {
      handleRank(i);
      rank -= 1;
      i = -1;
    }
  }
  return tasteRank;
};
export const profile = (req, res) => {
  const tastes = req.session.user.taste;
  const personal_tasteRank = checkTaste(tastes);
  return res.send(personal_tasteRank);
};
export const openProfile = async (req, res) => {
  const { id } = req.params;
  const user = await userModel.findById(id);
  const tastes = user.taste;
  const personal_tasteRank = checkTaste(tastes);

  return res.render("openProfile", {
    pageTitle: "Profile",
    personal_tasteRank,
  });
};
export const getEditProfile = (req, res) => {
  return res.render("editProfile", { pageTitle: "Edit Profile" });
};
export const postEditProfile = (req, res) => {
  return res.redirect("/users");
};
export const getTaste = (req, res) => {
  return res.render("taste", { pageTitle: "Taste" });
};
export const postTaste = async (req, res) => {
  const { _id } = req.session.user;
  console.log(req.session);
  req.session.user.taste.forEach((current, index) => {
    if (req.session.user.taste[index] != 0) {
      req.session.user.taste[index] += 1;
    }
  });
  req.session.user.taste[Number(req.body.taste)] += 1;
  const user = await userModel.findById(_id);
  user.taste = req.session.user.taste;
  req.session.user = user;
  await user.save();
  return res.send({"msg" : "Success"});
};
export const getInitialTaste = (req, res) => {
  req.session.user.taste.forEach((current, index) => {
    req.session.user.taste[index] = 0;
  });
  return res.redirect("/users/taste");
};
export const logout = async (req, res) => {
  const { _id } = req.session.user;
  const user = await userModel.findById(_id);
  user.userState = 0;
  await user.save();
  req.session.destroy();
  return res.redirect("/");
};
