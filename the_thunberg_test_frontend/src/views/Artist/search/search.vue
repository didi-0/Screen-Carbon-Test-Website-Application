<template>
  <div class="artist">
    <div class="tags-panel">
      <ul class="tags-lines">
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
            <th scope="col">Name</th>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Score</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(celeb, index) in celebsList" :key="index">
            <th scope="row">{{ index + 1 }}</th>
            <router-link :to="'/Artist?id=' + celeb.id">
              <td>{{ celeb.name }}</td>
            </router-link>
            <td>{{ celeb.name.split(" ")[0] }}</td>
            <td>{{ celeb.name.split(" ")[1] }}</td>
            <td>{{ celeb.popularity }}</td>
          </tr>
        </tbody>
      </table>
      <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center pt-3">
          <li class="page-item disabled">
            <a class="page-link" href="#" tabindex="-1" aria-disabled="true"
              >Previous</a
            >
          </li>
          <li class="page-item"><a class="page-link" href="#">1</a></li>
          <li class="page-item"><a class="page-link" href="#">2</a></li>
          <li class="page-item"><a class="page-link" href="#">3</a></li>
          <li class="page-item">
            <a class="page-link" href="#">Next</a>
          </li>
        </ul>
      </nav>
    </div>
  </div>
</template>
    
    <script>
import { PopularPeople } from "@/api/film";

export default {
  name: "search",
  data() {
    return {
      keywords: "",
      celebsList: [],
    };
  },
  mounted() {
    PopularPeople().then((res) => {
      this.celebsList = res.results;
    });
  },
};
</script>
    
<style scoped>
@import "../../../assets/css/movie-list.css";

.artist {
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
</style>
    