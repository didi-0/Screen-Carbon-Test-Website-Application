<template>
  <div class="tv">
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
              v-for="(genere, index) in tvGenresList"
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
              v-for="(certification, index) in tvCertifications"
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
                  style="height: 20px"
                />
                <button
                  class="btn btn-outline-secondary btn-sm d-flex justify-content-center align-items-center"
                  type="button"
                  @click="handleKeywords(keywords)"
                  style="height: 20px"
                >
                  Search
                </button>
                <button
                  class="btn btn-outline-secondary d-flex justify-content-center align-items-center btn-sm ml-3"
                  type="button"
                  style="height: 20px"
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
            <th scope="col">TV Shows Title</th>
            <th scope="col">First Air Date</th>
            <th scope="col">Score</th>
            <th scope="col">Rate</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(tv, index) in tvList" :key="index">
            <th scope="row">{{ index + 1 }}</th>
            <router-link :to="'/film?id=' + tv.id + '&media_type=tv'">
              <td>{{ tv.title }}</td>
            </router-link>
            <td>{{ tv.firstAirDate }}</td>
            <td>{{ tv.score.toFixed(2) }}</td>
            <td>
              <router-link :to="'/Rate?id=' + tv.id + '&media_type=tv'">
                <button class="btn-dark" style="height: 30px">Rate Now</button>
              </router-link>
            </td>
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
import { TVCertifications } from "@/api/film";
import { TVGenresList } from "@/api/film";
import { serverTVDiscovery } from "@/api/film";

export default {
  name: "search",
  data() {
    return {
      selectCertification: "All",
      selectGenere: "All",
      selectGenereId: null,
      keywords: "",
      selectSort: "popularity.desc",
      tvCertifications: [],
      tvGenresList: [],
      tvList: [],
      totalPages: 1,
      pageData: {
        page: 1,
        limit: 20,
      },
    };
  },
  mounted() {
    TVCertifications().then((res) => {
      this.tvCertifications = res.certifications.GB.map(
        (item) => item.certification
      );
      this.tvCertifications.unshift("All");
    });

    TVGenresList("en").then((res) => {
      this.tvGenresList = res.genres;
      this.tvGenresList.unshift({ id: null, name: "All" });
    });
    serverTVDiscovery(
      this.selectCertification === "All" ? "" : this.selectCertification,
      this.pageData.page,
      this.selectSort,
      this.selectGenereId
    ).then((res) => {
      this.tvList = res.data.records;
      console.log(this.tvList);
      console.log(res.data.pages);
      this.totalPages = res.data.pages > 500 ? 500 : res.data.pages;
    });
  },
  methods: {
    handleCertificationSelect(certification) {
      if (certification == "All") {
        this.selectCertification = "";
      } else {
        this.selectCertification = certification;
      }
      this.reloadTVList();
      if (certification == "All") {
        this.selectCertification = "All";
      }
    },

    handleGenereSelect(genere) {
      if (genere.name == "All") {
        this.selectGenere = "";
        this.selectGenereId = null;
      } else {
        this.selectGenere = genere.name;
        this.selectGenereId = genere.id;
      }
      this.reloadTVList();
      if (genere.name == "All") {
        this.selectGenere = "All";
        this.selectGenereId = null;
      }
    },

    handleSortSelect(sort) {
      this.selectSort = sort;
      this.reloadTVList();
    },

    handleKeywords(keywords) {
      this.keywords = keywords;
      this.reloadTVList();
    },

    reloadTVList() {
      serverTVDiscovery(
        this.selectCertification === "All" ? "" : this.selectCertification,
        this.pageData.page,
        this.selectSort,
        this.selectGenereId
      ).then((res) => {
        this.tvList = res.data.records;
        console.log(this.tvList);
        console.log(res.data.pages);
        this.totalPages = res.data.pages > 500 ? 500 : res.data.pages;
      });
    },

    handlePage(page) {
      this.pageData.page = page;
      this.reloadTVList();
    },
  },
};
</script>
  
  <style scoped>
@import "../../../assets/css/movie-list.css";

.tv {
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
.pager {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
  