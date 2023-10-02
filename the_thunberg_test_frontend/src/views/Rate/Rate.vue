<template>
  <div class="Rate">
    <carouselSlidesOnly :id="id" :mediaType="mediaType"></carouselSlidesOnly>
    <router-view :id="id" :mediaType="mediaType"></router-view>
  </div>
</template>

<script>
import carouselSlidesOnly from "./carouselSlidesOnly.vue";
import slider from "./slider.vue";

export default {
  name: "Rate",
  components: {
    carouselSlidesOnly,
    slider,
  },
  data() {
    return {
      id: this.$route.query.id,
      mediaType: this.$route.query.media_type,
    };
  },
};
</script>