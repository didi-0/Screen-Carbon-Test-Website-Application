<template>
  <section>
    <section>
      <div class="container">
        <div
          id="carouselExampleSlidesOnly"
          class="carousel slide"
          data-bs-ride="carousel"
        >
          <div class="carousel-inner">
            <div class="carousel-item active">
              <img
                id="rate-background"
                src=""
                class="d-block w-100"
                alt=""
                style="height: 500px"
              />
              <div class="carousel-caption d-none d-md-block">
                <div class="row">
                  <div class="col-6 mt-5">
                    <h1 class="text-dark">{{ detail.name }}</h1>
                    <div class="d-flex justify-content-center">
                      <button class="btn btn-dark mt-5">
                        Thunberg Score: 72%
                      </button>
                    </div>
                    <p class="text-dark mt-3">
                      {{ detail.gender === 1 ? "Female" : "Male"
                      }}<i class="bi bi-dot"></i>{{ detail.birthday
                      }}<i class="bi bi-dot"></i>{{ detail.place_of_birth }}
                    </p>
                    <div class="row justify-content-center"></div>
                  </div>
                  <div class="col-6">
                    <img
                      id="FilmPoster"
                      :src="
                        detail.profile_path === null ||
                        detail.profile_path === undefined
                          ? '#'
                          : baseUrl + detail.profile_path
                      "
                      style="width: 250px; height: 350px"
                    />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <section class="p-5 bg-dark">
      <div class="container">
        <h2 class="text-center text-light">Biography</h2>
        <p class="text-light">
          {{ detail.biography }}
        </p>
      </div>
    </section>
  </section>
</template>

<script>
import { DetailsPeople } from "@/api/film";
import "bootstrap-icons/font/bootstrap-icons.css";

export default {
  name: "Person",
  props: ["id"],
  data() {
    return {
      baseUrl: "https://image.tmdb.org/t/p/original",
      detail: [],
    };
  },
  mounted() {
    DetailsPeople(this.id, "en-US").then((res) => {
      this.detail = res;
    });
  },
};
</script>