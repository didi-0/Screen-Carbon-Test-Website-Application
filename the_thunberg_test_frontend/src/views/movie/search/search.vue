<template>
  <div class="films">
    <div class="tags-panel">
      <ul class="tags-lines">
        <li class="tags-line">
          <div class="tags-title">Sort</div>
          <ul class="tags">
            <li
              :class="selectSort === 'popularity.desc' ? 'active' : ''"
              @click="handleSortSelect('popularity.desc')"
            >
              Popularity Descending
            </li>
            <li
              :class="selectSort === 'popularity.asc' ? 'active' : ''"
              @click="handleSortSelect('popularity.asc')"
            >
              Popularity Ascending
            </li>
            <li
              :class="
                selectSort === 'primary_release_date.desc' ? 'active' : ''
              "
              @click="handleSortSelect('primary_release_date.desc')"
            >
              Release Date Ascending
            </li>
            <li
              :class="selectSort === 'primary_release_date.asc' ? 'active' : ''"
              @click="handleSortSelect('primary_release_date.asc')"
            >
              Release Date Descending
            </li>
          </ul>
        </li>
        <li class="tags-line">
          <div class="tags-title">Genres</div>
          <ul class="tags">
            <li
              v-for="(genere, index) in movieGenresList"
              :key="index"
              :class="selectGenere === genere.name ? 'active' : ''"
              @click="handleGenereSelect(genere)"
            >
              {{ genere.name }}
            </li>
          </ul>
        </li>
        <li class="tags-line">
          <div class="tags-title">Certification</div>
          <ul class="tags">
            <li
              v-for="(certification, index) in movieCertifications"
              :key="index"
              :class="selectCertification === certification ? 'active' : ''"
              @click="handleCertificationSelect(certification)"
            >
              {{ certification }}
            </li>
          </ul>
        </li>

        <li class="tags-line">
          <div class="tags-title">Keywords</div>
          <ul class="tags">
            <li>
              <div class="input-group d-flex align-items-center">
                <input
                  type="text"
                  class="form-control d-flex justify-content-center align-items-center"
                  placeholder="filter by keywords"
                  style="height: 20px; font-size: 12px"
                  v-model="keywords"
                />
                <button
                  class="btn btn-outline-secondary btn-sm d-flex justify-content-center align-items-center"
                  type="button"
                  @click="handleKeywords()"
                  style="height: 20px"
                >
                  Search
                </button>
                <button
                  class="btn btn-outline-secondary d-flex justify-content-center align-items-center btn-sm ml-3"
                  type="button"
                  style="height: 20px"
                  @click="download"
                >
                  Download CSV
                </button>
              </div>
            </li>
          </ul>
        </li>
      </ul>
    </div>
    <div class="table pt-3">
      <table class="table table-bordered">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Movie Title</th>
            <th scope="col">Release Year</th>
            <th scope="col">Producer</th>
            <th scope="col">Director</th>
            <th scope="col">Score</th>
            <th scope="col">Rate</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(film, index) in filmList" :key="index">
            <th scope="row">{{ index + 1 }}</th>
            <router-link :to="'/film?id=' + film.id + '&media_type=movie'">
              <td>{{ film.title }}</td>
            </router-link>
            <td>{{ film.releaseYear }}</td>
            <td>{{ film.director }}</td>
            <td>{{ film.producer }}</td>
            <td>{{ film.score.toFixed(2) }}</td>
            <router-link :to="'/Rate?id=' + film.id + '&media_type=movie'">
              <td>
                <button class="btn-dark" style="height: 30px">Rate Now</button>
              </td>
            </router-link>
          </tr>
        </tbody>
      </table>
      <div class="pager">
        <el-pagination
          layout="prev, pager, next"
          :page-count="totalPages" 
          @current-change="handlePage"
        >
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import { MovieCertifications } from "@/api/film";
import { MovieGenresList } from "@/api/film";
import { serverMovieDiscovery } from "@/api/film";
import axios from "axios";

export default {
  name: "search",
  data() {
    return {
      selectCertification: "All",
      selectGenere: "All",
      selectGenereId: null,
      keywords: "",
      selectSort: "popularity.desc",
      movieCertifications: [],
      movieGenresList: [],
      filmList: [],
      totalPages: 1,
      pageData: {
        page: 1,
        limit: 20,
      },
    };
  },
  mounted() {
    MovieCertifications().then((res) => {
      this.movieCertifications = res.certifications.GB.map(
        (item) => item.certification
      );
      this.movieCertifications.unshift("All");
    });

    MovieGenresList("en").then((res) => {
      this.movieGenresList = res.genres;
      this.movieGenresList.unshift({ id: null, name: "All" });
    });

    serverMovieDiscovery(
      this.selectCertification === "All" ? "" : this.selectCertification,
      this.pageData.page,
      this.selectSort,
      this.selectGenereId,
      this.keywords
    ).then((res) => {
      console.log(res.data.pages);
      this.filmList = res.data.records;
      this.totalPages = res.data.pages > 500? 500 : res.data.pages;
    });
  },
  methods: {
    handleCertificationSelect(certification) {
      this.selectCertification = certification;
      this.reloadFilmList();
    },

    handleGenereSelect(genere) {
      if (genere.name === "All") {
        this.selectGenere = "";
        this.selectGenereId = null;
      } else {
        this.selectGenere = genere.name;
        this.selectGenereId = genere.id;
      }
      this.reloadFilmList();
      if (genere.name === "All") {
        this.selectGenere = "All";
        this.selectGenereId = null;
      }
    },

    handleSortSelect(sort) {
      this.selectSort = sort;
      this.reloadFilmList();
    },

    handleKeywords() {
      this.reloadFilmList();
    },

    reloadFilmList() {
      serverMovieDiscovery(
        this.selectCertification === "All" ? "" : this.selectCertification,
        this.pageData.page,
        this.selectSort,
        this.selectGenereId,
        this.keywords
      ).then((res) => {
        this.filmList = res.data.records;
        console.log(res.data.pages);
        this.totalPages = res.data.pages > 500? 500 : res.data.pages;
      });
    },
    handlePage(page) {
      this.pageData.page = page;
      this.reloadFilmList();
    },
    download() {
      axios({
        method: "get",
        url:
          "http://localhost:8889/download/downloadMovie?certification=" +
          this.selectCertification +
          "&certification_country=GB&include_adult=false&include_video=false&language=en-US&sort_by=" +
          this.selectSort +
          "&with_genres=" +
          this.selectGenereId +
          "&with_keywords=" +
          this.keywords, // 请求地址
        responseType: "blob",
        headers: {
          "Content-Type": "application/json",
        },
      }).then((res) => {
        const blob = new Blob([res.data], { type: "application/vnd.ms-excel" });
        let date = new Date();
        const fileName =
          "movie-green-rate" + date.toISOString().split("T")[0] + ".xlsx";
        const elink = document.createElement("a");
        elink.download = fileName;
        elink.style.display = "none";
        elink.href = URL.createObjectURL(blob);
        document.body.appendChild(elink);
        elink.click();
        URL.revokeObjectURL(elink.href);
        document.body.removeChild(elink);
      });
    },
  },
};
</script>

<style scoped>
@import "../../../assets/css/movie-list.css";

.films {
  padding: 20px 220px;
}

.el-card__body {
  padding: 0 0 10px;
}

.s {
  margin-bottom: 10px;
  padding: 0 8px;
  letter-spacing: 1px;
  color: coral;
  text-align: center;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

#searchBarUl {
  margin-left: 0px;
}
.pager {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
