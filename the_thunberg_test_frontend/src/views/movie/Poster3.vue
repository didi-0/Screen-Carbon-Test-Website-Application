<template>
  <section class="p-5 bg-dark">
    <div class="container">
      <div class="little-title d-flex">
        <img class="bi bi-balloon-heart" />
        <h2 class="text-center text-light">
          <i class="bi bi-three-dots-vertical"></i>DIRECTOR, WRITERS and
          PRODUCERS
        </h2>
      </div>
      <div class="card-group">
        <div class="row">
          <div
            v-for="(director, index) in directorList"
            :key="index"
            class="col"
          >
            <div class="card" style="height: 500px">
              <router-link :to="'/Artist?id=' + director.id">
                <img
                  :src="
                    director.profile_path === null || director.profile_path === undefined
                      ? '#'
                      : baseUrl + director.profile_path
                  "
                  class="card-img-top"
                  alt="..."
                  style="height: 375px; width: 238px"
                />
              </router-link>
              <div class="card-body">
                <span
                  class="glyphicon glyphicon-star"
                  aria-hidden="true"
                ></span>
                <div class="d-flex">
                  <h3 class="card-title">
                    <i class="bi bi-star-fill"></i> 3.5
                  </h3>
                </div>
                <h5 class="card-text">
                  {{ director.job }}
                </h5>
                <router-link :to="'/Artist?id=' + director.id">
                  <h5 class="card-text">
                    {{ director.name }}
                  </h5>
                </router-link>
              </div>
            </div>
          </div>
          <div
            v-for="(producer, index) in producerList"
            :key="index + 'producer'"
            class="col"
          >
            <div class="card" style="height: 500px">
              <router-link :to="'/Artist?id=' + producer.id">
                <img
                  :src="
                    producer.profile_path === null || producer.profile_path === undefined
                      ? '#'
                      : baseUrl + producer.profile_path
                  "
                  class="card-img-top"
                  alt="..."
                  style="height: 375px; width: 238px"
                />
              </router-link>
              <div class="card-body">
                <span
                  class="glyphicon glyphicon-star"
                  aria-hidden="true"
                ></span>
                <div class="d-flex">
                  <h3 class="card-title">
                    <i class="bi bi-star-fill"></i> 3.5
                  </h3>
                </div>
                <h5 class="card-text">
                  {{ producer.job }}
                </h5>
                <router-link :to="'/Artist?id=' + producer.id">
                  <h5 class="card-text">
                    {{ producer.name }}
                  </h5>
                </router-link>
              </div>
            </div>
          </div>
          <div v-for="(writer, index) in writerList" :key="index + 'writer'" class="col">
            <div class="card" style="height: 500px">
              <router-link :to="'/Artist?id=' + writer.id">
                <img
                  :src="
                    writer.profile_path === null || writer.profile_path === undefined
                      ? '#'
                      : baseUrl + writer.profile_path
                  "
                  class="card-img-top"
                  alt="..."
                  style="height: 375px; width: 238px"
                />
              </router-link>
              <div class="card-body">
                <span
                  class="glyphicon glyphicon-star"
                  aria-hidden="true"
                ></span>
                <div class="d-flex">
                  <h3 class="card-title">
                    <i class="bi bi-star-fill"></i> 3.5
                  </h3>
                </div>
                <h5 class="card-text">
                  {{ writer.job }}
                </h5>
                <router-link :to="'/Artist?id=' + writer.id">
                  <h6 class="card-text">
                    {{ writer.name }}
                  </h6>
                </router-link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>
  
  <script>
import { Credits } from "@/api/film";
import { CreditsTV } from "@/api/film";

export default {
  name: "Poster3",
  props: ["id", "mediaType"],
  data() {
    return {
      baseUrl: "https://image.tmdb.org/t/p/original",
      writerList: [],
      producerList: [],
      directorList: [],
      crew: [],
    };
  },
  created() {
    if (this.mediaType === "movie") {
      Credits(this.id, "en-US").then((res) => {
        this.crew = res.crew;
        this.directorList = this.crew.filter(
          (director) => director.job === "Director"
        );
        this.directorList = this.directorList.splice(0, 2);
        this.$store.commit("setDirectorList", this.directorList);

        this.producerList = this.crew.filter(
          (producer) => producer.job === "Producer"
        );
        this.producerList = this.producerList.splice(0, 2);
        this.$store.commit("setProducerList", this.producerList);

        this.writerList = this.crew.filter((writer) => writer.job === "Writer");
        this.writerList = this.writerList.splice(0, 1);
        this.$store.commit("setWriterList", this.writerList);
      });
    } else {
      CreditsTV(this.id, "en-US").then((res) => {
        this.crew = res.crew;
        this.directorList = this.crew.filter(
          (director) => director.job === "Director"
        );
        this.directorList = this.directorList.splice(0, 2);
        this.$store.commit("setDirectorList", this.directorList);

        this.producerList = this.crew.filter(
          (producer) => producer.job === "Producer"
        );
        this.producerList = this.producerList.splice(0, 2);
        this.$store.commit("setProducerList", this.producerList);

        this.writerList = this.crew.filter((writer) => writer.job === "Writer");
        this.writerList = this.writerList.splice(0, 1);
        this.$store.commit("setWriterList", this.writerList);
      });
    }
  },
};
</script>