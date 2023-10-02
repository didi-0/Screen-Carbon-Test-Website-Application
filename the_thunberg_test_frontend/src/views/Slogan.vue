<template>
  <section class="p-5 bg-dark">
    <div class="container d-flex">
      <div class="row row-cols-2">
        <div class="col">
          <div class="little-title">
            <h2 class="text-left text-light">
              {{ title }}
            </h2>
          </div>
          <div class="row row-cols-2">
            <div
              v-for="(bulletinBoard, index) in bulletinBoardList"
              :key="index"
              v-if="bulletinBoard.number === 0"
              class="col pt-3"
            >
              <div class="card bg-success border-light rounded-lg">
                <div class="card-body" style="width: auto; height: 300px">
                  <!-- <h5 class="card-title">
                    {{ bulletinBoard.subtitle }}
                  </h5> -->
                  <p class="card-text">
                    {{ bulletinBoard.content }}
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div
          v-for="bulletinBoard in bulletinBoardList"
          :key="bulletinBoard.id"
          v-if="bulletinBoard.number === 1"
          class="col"
        >
          <div class="little-title2">
            <h2 class="text-center text-light">{{ bulletinBoard.title }}</h2>
          </div>
          <div
            class="card bg-light border-success text-success"
            style="width: auto; height: 640px"
          >
            <div class="card-body">
              <div class="text-wrapper" style="white-space: pre-wrap">
                <p class="card-text">
                  {{ bulletinBoard.content }}
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { selectBulletinBoard } from "@/api/film";

export default {
  name: "Slogan",
  data() {
    return {
      bulletinBoardList: [],
      title: "",
    };
  },
  mounted() {
    selectBulletinBoard().then((res) => {
      this.bulletinBoardList = res.data;
      this.title = this.bulletinBoardList[0].title;
    });
  },
};
</script>