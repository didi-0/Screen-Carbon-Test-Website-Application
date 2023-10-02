<template>
  <div class="filmDetail">
    <carouselOnly :id="id" :media-type="mediaType"></carouselOnly>
    <Poster3 :id="id" :media-type="mediaType"></Poster3>
    <Poster4 :id="id" :media-type="mediaType"></Poster4>
    <furtherRate></furtherRate>
  </div>
</template>

<script>
import carouselOnly from "./carouselOnly.vue";
import Poster3 from "./Poster3.vue";
import Poster4 from "./Poster4.vue";
import furtherRate from "./furtherRate.vue";

export default {
  name: "filmDetail",
  components: { carouselOnly, Poster3, Poster4, furtherRate },
  data() {
    return {
      id: this.$route.query.id,
      mediaType: this.$route.query.media_type,
    };
  },
};
</script>