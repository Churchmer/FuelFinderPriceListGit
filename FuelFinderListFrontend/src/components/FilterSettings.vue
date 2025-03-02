<script setup>
import axios from 'axios';
import GasStationListElement from './GasStationListElement.vue';
</script>

<template>
    <div class="filter-settings-container">
        <!-- User input field for postal code -->
        <input id="locationInput"
            placeholder="Postleitzal"
            class="location-input"
            v-model="locationInput">   

        <!-- Dropdown for selecting distance range -->
        <select v-model="selectedValueDistance" class="distance-dropdown">
            <option v-for="(option, index) in optionsDistance" :key="index" :value="option.value" v-show="option.value !== 'Entfernung'">
                {{ option.text }}
            </option>
        </select>

        <!-- Dropdown for selecting fuel type -->
        <select v-model="selectedValueFuel" class="fuel-dropdown" placeholder="Kraftstoff">
            <option v-for="(option, index) in optionsFuel" :key="index" :value="option.value" v-show="option.value !== 'Kraftstoff'">
                {{ option.text }}
            </option>
        </select>

        <!-- Dropdown for sorting preference -->
        <select v-model="selectedValueSort" class="sort-dropdown">
            <option v-for="(option, index) in optionsSorting" :key="index" :value="option.value">
                {{ option.text }}
            </option>
        </select>

        <!-- Button that triggers the gas station search -->
        <button class="search-button" @click.prevent="loadData()" >Suchen</button>
    </div>

    <!-- Component to display list of gas stations variable depending on how much stations there are -->
    <div class="list-elements">
        <GasStationListElement :stations="gasStations" />
    </div>

    <!-- Display errors prominently -->
    <div v-if="errorMessage" class="error-message">
            {{ errorMessage }}
    </div>
</template>

<script>
    export default { 
        name: 'FilterSettings',
        components: {
            GasStationListElement
        },
        data() {
            return {
                locationInput: '',                      // Stores the user-input postal code
                selectedValueDistance: 'Entfernung',    // Default distance selection
                selectedValueFuel: 'Kraftstoff',        // Default fuel type selection
                selectedValueSort: 'Price Asc',         // Default sorting order
                errorMessage: "",                       // Message displayed in case of an error
                optionsDistance: [                      // Distance options for dropdown    
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
                optionsFuel: [                          // Fuel type options for dropdown
                    { value: 'Diesel', text: 'Diesel' },
                    { value: 'Super E5', text: 'Super E5' },
                    { value: 'Super E10', text: 'Super E10' },
                    { value: 'Kraftstoff', text: 'Kraftstoff' }
                ],
                optionsSorting: [                       // Sorting options for dropdown
                    { value: 'Price Asc', text: 'Preis'},
                    { value: 'Dist Asc', text: 'Entfernung'}
                ],
                gasStations: []                         // List of fetched gas stations
            };
        },
        methods: {
            loadData() {
                // Validate inputs before fetching data
                if (!/^\d{5}$/.test(this.locationInput)) {
                    this.errorMessage = 'ungültige Postleitzahl!';
                } else if (this.selectedValueDistance === 'Entfernung' ) {
                    this.errorMessage = 'Bitte Entfernung angeben';
                } else if (this.selectedValueFuel === 'Kraftstoff') {
                    this.errorMessage = 'Bitte Kraftstoff angeben';
                } else {
                    this.errorMessage = '';
                    this.fetchGasStations();
                }
            },
            fetchGasStations() {
                const distance = parseInt(this.selectedValueDistance);  // Convert selection to number
                const fuelType = this.selectedValueFuel;
                const postalCode = this.locationInput;
                const sortOrder = this.selectedValueSort;

                // Perform an API call to retrieve gas stations based on input
                axios.get('http://localhost:9090/api/messages/stations', {
                    params: {
                        rad: distance,
                        fuelType: fuelType,
                        postalCode: postalCode,
                        sortOrder: sortOrder
                    }
                })
                .then(response => {
                    // Update station list upon successful response
                    this.gasStations = JSON.parse(JSON.stringify(response.data));
                    console.log(this.gasStations);
                })
                .catch(error => {
                    // Set an appropriate error message based on the error type
                    if (error.response) {
                        this.errorMessage = `Fehler: ${error.response.data.message || 'Unerwarteter Fehler'}`;
                    } else if (error.request) {
                        this.errorMessage = 'Es konnte keine Antwort vom Server empfangen werden. Bitte prüfen Sie Ihre Netzwerkverbindung.';
                    } else {
                        this.errorMessage = `Anfragenfehler: ${error.message}`;
                    }
                    console.error('Error fetching gas stations:', error);
                });
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
        width: 150px;
        margin-right: 1%;
        border-radius: 8px;
    }

    .distance-dropdown {
        width: 90px;
        height: 30px;
        margin-right: 4%;
        border-radius: 8px;
        border: transparent;
        cursor: pointer;
    }

    .fuel-dropdown {
        width: 90px;
        height: 30px;
        margin-right: 4%;
        border-radius: 8px;
        border: transparent;
        cursor: pointer;
    }

    .sort-dropdown {
        width: 90px;
        height: 30px;
        margin-right: 4%;
        border-radius: 8px;
        border: transparent;
        cursor: pointer;
    }

    .search-button {
        border-radius: 8px;
        width: 110px; 
        height: 30px;
        background-color: yellow;
        border: yellow;
        float: right;
        cursor: pointer;
        font-weight: 600;
    }

    .list-elements {
        margin-top: 20px;
    }

    .error-message {
        text-align: center;
        color: red;
        font-weight: bold;
        margin-top: 10px;
        margin-left: 25%;
        margin-right: 25%;
        border: 1px solid rgb(59, 59, 59);
        border-radius: 8px;
        padding: 16px;
    }
</style>