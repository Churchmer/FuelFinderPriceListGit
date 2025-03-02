<template>
    <!-- Header section displaying the application name and logo -->
    <header class='page-header'>
        FuelFinder
        <img src="./icons/FuelFinderLogoV2.webp" alt="Fuel Finder Logo" class="header-logo">
    </header>
</template>

<script>
    export default {
        name:'PageHeader'
    }
</script>

<style scoped>
    .page-header {
        display: flex;
        line-height: 1;
        justify-content: center;
        align-items: center;
        max-height: 100vh;
        background-color: yellow;
        color: black;
        font-family: Copperplate,  "Copperplate Gothic Light", fantasy;
        font-size: xx-large;
        position: relative;
    }

    .header-logo {
        width: 50px;
        height: 50px;
    }
</style>