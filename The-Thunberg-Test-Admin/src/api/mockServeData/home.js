import Mock from "mockjs";

let list = [];
export default {
  getStatisticalData: () => {
    for (let i = 0; i < 7; i++) {
      list.push(
        Mock.mock({
          苹果: Mock.Random.float(100, 8000, 0, 0),
          vivo: Mock.Random.float(100, 8000, 0, 0),
          oppo: Mock.Random.float(100, 8000, 0, 0),
          魅族: Mock.Random.float(100, 8000, 0, 0),
          小米: Mock.Random.float(100, 8000, 0, 0),
        })
      );
    }
    return {
      code: 20000,
      data: {
        videoData: [
          { name: "小米", value: 2999 },
          { name: "小米", value: 2999 },
        ],
        userData: [
          {
            date: "周一",
            new: 10,
            active: 550,
          },
          {
            date: "周一",
            new: 10,
            active: 550,
          },
        ],
        orderData: {
          date: ["20191001"],
          data: list,
        },
        tableData: [
          {
            date: "2016-05-02",
            name: "Jack",
            rate: "3.5",
            clock: "11:17",
          },
          {
            date: "2016-05-04",
            name: "Jack",
            rate: "3.5",
            clock: "11:17",
          },
          {
            date: "2016-05-01",
            name: "Jack",
            rate: "3.5",
            clock: "11:17",
          },
          {
            date: "2016-05-03",
            name: "Jack",
            rate: "3.5",
            clock: "11:17",
          },
        ],
      },
    };
  },
};
