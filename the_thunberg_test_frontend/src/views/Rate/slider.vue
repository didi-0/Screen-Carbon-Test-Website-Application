<template>
  <section>
    <div class="container">
      <div class="app pb-5">
        <div class="text-center mt-3">
          <h3>{{ survey.title }} – {{ survey.description }}</h3>
        </div>
        <template>
          <div
            v-for="(question, index) in survey.questionList"
            :key="index"
            class="block mt-3"
          >
            <span class="demonstration">{{ question.content }}</span>
            <el-slider
              v-model="values[index]"
              :min="0"
              :max="10"
              :step="0.1"
              :marks="marks"
              :show-tooltip="false"
              :input-size="sliderInputSize"
              show-stops
            >
            </el-slider>
          </div>
        </template>
      </div>
      <div class="app pb-5">
        <div class="text-center mt-3">
          <h3>{{ survey1.title }} – {{ survey1.description }}</h3>
        </div>
        <template>
          <div
            v-for="(question, index) in survey1.questionList"
            :key="index"
            class="block mt-3"
          >
            <span class="demonstration">{{ question.content }}</span>
            <el-slider
              v-model="values1[index]"
              :step="0.1"
              :min="0"
              :max="10"
              :marks="marks"
              :show-tooltip="false"
              :input-size="sliderInputSize"
              show-stops
            >
            </el-slider>
          </div>
        </template>
      </div>
      <div class="app pb-5">
        <div class="text-center mt-3">
          <h3>{{ survey2.title }} – {{ survey2.description }}</h3>
        </div>
        <template>
          <div
            v-for="(question, index) in survey2.questionList"
            :key="index"
            class="block mt-3"
          >
            <span class="demonstration">{{ question.content }}</span>
            <el-slider
              v-model="values2[index]"
              :step="0.1"
              :min="0"
              :max="10"
              :marks="marks"
              :show-tooltip="false"
              :input-size="sliderInputSize"
              show-stops
            >
            </el-slider>
          </div>
        </template>
      </div>
      <div class="d-flex justify-content-center mt-3 mb-5">
        <router-link to="/Rate/Congratulations"
          ><button class="btn btn-dark" @click="submitRate">Finished</button></router-link
        >
      </div>
    </div>
  </section>
</template>

<script>
import { serverQueryQuestion } from "@/api/film";
import { serverUserRate } from "@/api/film";

export default {
  name: "slider",
  props: ["id", "mediaType"],
  data() {
    return {
      sliderInputSize: "mini",
      marks: {
        0: "Strongly agree",
        2.5: "Agree ",
        5: "Neither agree nor disagree",
        7.5: "Disagree ",
        10: "Strongly disagree",
      },
      surveyIds: [7, 8, 9],
      survey: [],
      survey1:[],
      survey2:[],
      valueAll:[],
      values:[],
      values1:[],
      values2:[]
    };
  },
  methods: {
    submitRate() {
      const requestData = {
        id: this.id,
        mediaType: this.mediaType,
        values: this.values.concat(this.values1, this.values2),
      };
      console.log(requestData)
      serverUserRate(requestData).then((res) => {

      });
    },
  },
  mounted() {
    serverQueryQuestion(this.surveyIds).then((res) => {
      this.survey = res.data[0];
      this.survey1 = res.data[1];
      this.survey2 = res.data[2];
    });
  },
};
</script>