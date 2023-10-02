<template>
  <section>
    <div class="container">
      <p class="name d-flex justify-content-center mt-5">Congratulation!</p>
      <p class="name2 d-flex justify-content-center">
        You've completed the questionnaire
      </p>
      <div class="d-flex justify-content-center mt-5">
        <router-link :to="'/film?id=' + id + '&media_type=' + mediaType">
          <button class="btn btn-dark btn-sm mb-5">NEXT</button>
        </router-link>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "congratulations",
  props: ["id", "mediaType"],
  components: {},
};
</script>

