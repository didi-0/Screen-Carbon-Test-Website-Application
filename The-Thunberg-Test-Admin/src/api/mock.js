import Mock from "mockjs";
import home from "./mockServeData/home";

Mock.mock("/api/Home/getData",home.getStatisticalData);

