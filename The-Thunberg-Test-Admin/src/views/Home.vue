<template>
  <el-row>
    <el-col :span="8" style="padding-right: 10px">
      <el-card class="box-card">
        <div class="user">
          <img src="../assets/logo.png" alt="" />
          <div class="userinfo">
            <p class="name">Admin</p>
            <p class="access">Super Admin</p>
          </div>
        </div>
        <div class="login-info">
          <p>Last login time: <span>2023-8-9</span></p>
        </div>
      </el-card>

      <el-card style="margin-top: 20px; height: 281px">
        <el-table :data="tableData" style="width: 100%">
          <el-table-column
            v-for="(val, key) in tableLabel"
            :key="key"
            :prop="val"
            :label="key"
          />
        </el-table>
      </el-card>
    </el-col>

    <el-col :span="16" style="padding-left: 10px">
      <el-card style="height: 280px">
        <div ref="echarts1" style="height: 280px"></div>
      </el-card>
      <div class="graph">
        <el-card style="height: 260px">
          <div ref="echarts2" style="height: 260px"></div>
        </el-card>
        <el-card style="height: 260px">
          <div ref="echarts3" style="height: 260px"></div>
        </el-card>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import { getData } from "../api";
import * as echarts from "echarts";

export default {
  name: "Home",
  data() {
    return {
      tableData: [],
      tableLabel: {
        Date: "date",
        Clock: "clock",
        Name: "name",
        Rate: "rate",
      },
    };
  },
  mounted() {
    getData().then(({ data }) => {
      const { tableData } = data.data;
      this.tableData = tableData;

      //基于准备好的dom,初始化echarts实例
      const charts1 = echarts.init(this.$refs.echarts1);
      //指定图标的配置项和数据
      var charts1Option = {};
      // 处理数据
      const { orderData } = data.data;
      const xAxis = Object.keys(orderData.data[0]);
      const xAxisData = {
        data: xAxis,
      };
      charts1Option.xAxis = xAxisData;
      charts1Option.yAxis = {};
      charts1Option.legend = xAxisData;
      charts1Option.series = [];
      xAxis.forEach((key) => {
        charts1Option.series.push({
          name: key,
          data: orderData.data.map((item) => item[key]),
          type: "line",
        });
      });
      //使用刚指定的配置项和数据显示图表
      charts1.setOption(charts1Option);

      //柱状图
      const charts2 = echarts.init(this.$refs.echarts2);
      var charts2Option = {
        title: {
          text: "Waterfall Chart",
          subtext: "Living Expenses in Shenzhen",
        },
        xAxis: {
          type: "category",
          data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
        },
        yAxis: {
          type: "value",
        },
        series: [
          {
            data: [120, 200, 150, 80, 70, 110, 130],
            type: "bar",
          },
        ],
      };
      charts2.setOption(charts2Option);
      console.log(charts2Option);

      //扇形图
      const charts3 = echarts.init(this.$refs.echarts3);
      var charts3Option = {
        title: {
          text: "Referer of a Website",
          subtext: "Fake Data",
          left: "left",
        },
        tooltip: {
          trigger: "item",
        },
        series: [
          {
            name: "Access From",
            type: "pie",
            radius: "50%",
            data: [
              { value: 1048, name: "Search Engine" },
              { value: 735, name: "Direct" },
              { value: 580, name: "Email" },
              { value: 484, name: "Union Ads" },
              { value: 300, name: "Video Ads" },
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
          },
        ],
      };
      charts3.setOption(charts3Option);
    });
  },
};
</script>

<style lang="less" scoped>
.user {
  padding-bottom: 20px;
  margin-bottom: 20px;
  border-bottom: 1px solid #ccc;
  display: flex;
  align-items: center;
  img {
    margin-right: 40px;
    width: 150px;
    height: 150px;
    border-radius: 50%;
  }
  .userinfo {
    .name {
      font-size: 32px;
      margin-bottom: 10px;
    }
    .access {
      color: grey;
    }
  }
}
.login-info {
  p {
    line-height: 28px;
    font-size: 14px;
    color: #999999;
    span {
      color: #666666;
      margin-left: 60px;
    }
  }
}
.graph {
  display: flex;
  justify-content: space-between;
  .el-card {
    width: 48%;
    margin-top: 20px;
  }
}
</style>