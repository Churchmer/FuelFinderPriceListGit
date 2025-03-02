<script setup>
import { RouterView } from 'vue-router'
</script>

<template>
  <div id="app">
    <!-- Displays the component for the current route -->
    <RouterView />
  </div>

</template>

<style>
  /* Global style reset for body margin */
  body {
    margin: 0;
  }
</style>
