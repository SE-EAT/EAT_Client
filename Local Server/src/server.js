import express from "express";
import morgan from "morgan";
import session from "express-session";
import flash from "express-flash";
import MongoStore from "connect-mongo";
import rootRouter from "./routers/rootRouter";
import userRouter from "./routers/userRouter";
import matchingRouter from "./routers/matchingRouter";
import recommendRouter from "./routers/recommendRouter";
import feedbackRouter from "./routers/feedbackRouter";
import { localMiddleware } from "./middlewares";

const app = express();
const logger = morgan("dev");

app.use(express.json());
app.set("view engine", "pug");
app.set("views", `${process.cwd()}/src/views`);
app.use(logger);
app.use(express.urlencoded({ extended: true }));
app.use(
  session({
    secret: "qwer",
    resave: false,
    saveUninitialized: false,
    store: MongoStore.create({ mongoUrl: "mongodb://127.0.0.1:27017/" }),
  })
);
app.use(flash());
app.use(localMiddleware);

app.use("/public", express.static(__dirname + "/public"));
app.use("/", rootRouter);
app.use("/users", userRouter);
app.use("/matching", matchingRouter);
app.use("/recommend", recommendRouter);
app.use("/feedback", feedbackRouter);

export default app;
