<template>
    <div class="filter-settings-container">
        <input id="locationInput"
            placeholder="Postleitzal..."
            class="location-input"
            v-model="locationInput">        
        <select v-model="selectedValueDistance" class="distance-dropdown">
            <option v-for="(option, index) in optionsDistance" :key="index" :value="option.value" v-show="option.value !== 'Entfernung'">
                {{ option.text }}
            </option>
        </select>
        <select v-model="selectedValueFuel" class="fuel-dropdown" placeholder="Kraftstoff">
            <option v-for="(option, index) in optionsFuel" :key="index" :value="option.value" v-show="option.value !== 'Kraftstoff'">
                {{ option.text }}
            </option>
        </select>
        <button class="suche-button" @click.prevent="validatePostalCode" style="cursor: pointer;">Suchen</button>
    </div>
</template>

<script>
    export default {
        name: 'FilterSettings',
        data() {
            return {
            locationInput: '',
            selectedValueDistance: 'Entfernung',
            selectedValueFuel: 'Kraftstoff',
            optionsDistance: [
                { value: '1 Km', text: '1 Km' },
                { value: '2 Km', text: '2 Km' },
                { value: '3 Km', text: '3 Km' },
                { value: '4 Km', text: '4 Km' },
                { value: '5 Km', text: '5 Km' },
                { value: '6 Km', text: '6 Km' },
                { value: '8 Km', text: '8 Km' },
                { value: '10 Km', text: '10 Km' },
                { value: '12 Km', text: '12 Km' },
                { value: '15 Km', text: '15 Km' },
                { value: '20 Km', text: '20 Km' },
                { value: 'Entfernung', text: 'Entfernung'}
            ],
            optionsFuel: [
                { value: 'Diesel', text: 'Diesel' },
                { value: 'Super E5', text: 'Super E5' },
                { value: 'Super E10', text: 'Super E10' },
                { value: 'HVO Diesel', text: 'HVO Diesel' },
                { value: 'Premium Diesel', text: 'Premium Diesel' },
                { value: 'Super 100+', text: 'Super 100+' },
                { value: 'Super Plus', text: 'Super Plus' },
                { value: 'AdBlue', text: 'AdBlue' },
                { value: 'Autogas', text: 'Autogas' },
                { value: 'CNG', text: 'CNG' },
                { value: 'Kraftstoff', text: 'Kraftstoff' }
            ]
            }
        },
        methods: {
            validatePostalCode() {
                if (!/^\d{5}$/.test(this.locationInput)) {
                    this.locationInput = "ungültige Postleitzahl!";
                }
            }
        }
    }
    
</script>

<style scoped>
    .filter-settings-container {
        border: 1px solid rgb(59, 59, 59);
        padding: 12px;
        border-radius: 8px;
        margin-top: 30px;
        margin-left: 25%;
        margin-right: 25%;
    }

    .location-input {
        height: 25px;
        width: 180px;
        margin-right: 4%;
        border-radius: 8px;
    }

    .distance-dropdown {
        width: 100px;
        height: 30px;
        margin-right: 4%;
        border-radius: 8px;
        border: transparent;
    }

    .fuel-dropdown {
        width: 80px;
        height: 30px;
        margin-right: 4%;
        border-radius: 8px;
        border: transparent;
    }

    .suche-button {
        border-radius: 8px;
        width: 100px; 
        height: 30px;
        background-color: yellow;
        border: yellow;
        float: right;
    }

    /* .invalid-input {
        color: red;
        font-family: "Monospace", Courier New;
    } */
</style>