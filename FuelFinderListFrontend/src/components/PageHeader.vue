<template>
    <header class='page-header'>
        FuelFinder
        <img src="./icons/FuelFinderLogoV2.webp" alt="Logo" class="header-logo">
    </header>
    <div>
        
    </div>
</template>

<script>
    export default {
        name:'PageHeader'
    }
</script>

<style scoped>
    .page-header {
        line-height: 1;
        max-height: 100vh;
        background-color: yellow;
        text-align: center;
        color: black;
        font-family: Copperplate,  "Copperplate Gothic Light", fantasy;
        font-size: xx-large;
    }
    .header-logo {
        width: 50px;
        height: 50px;
        align-items: right;
        vertical-align: bottom;
    }
</style>