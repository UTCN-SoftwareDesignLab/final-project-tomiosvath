<template>
    <v-dialog
            transition="dialog-bottom-transition"
            max-width="600"
            :value="opened"
    >
        <template>
            <v-card>
                <v-toolbar color="primary" dark>
                    {{ isNew ? "Create Customer" : "Operations" }}
                </v-toolbar>
                <v-form>
                    <v-text-field v-model = "customer.name" label = "Name" />
                    <v-text-field v-model = "customer.licenseNb" label = "Driving License number" />
                    <v-text-field v-model = "customer.personalNb" label = "Personal Identification number" />
                    <v-text-field v-model = "customer.phoneNb" label = "Phone number" />
                    <v-text-field v-model = "customer.address" label = "Address" />
                </v-form>
                <v-card-actions>
                    <v-btn @click="persist">
                        {{ isNew ? "Create" : "Save" }}
                    </v-btn>
                    <v-btn @click="deleteCustomer">Delete customer</v-btn>
                </v-card-actions>
            </v-card>
        </template>
    </v-dialog>
</template>

<script>
    import api from "../api";

    export default {
        name: "CustomerDialog",
        props: {
            customer: Object, 
            opened: Boolean,
        },
        methods: {
            persist() {
                if (this.isNew) {
                    api.customers
                        .create({
                            name: this.customer.name,
                            licenseNb: this.customer.licenseNb,
                            personalNb: this.customer.personalNb,
                            phoneNb: this.customer.phoneNb,
                            address: this.customer.address,
                        })
                        .then(() => this.$emit("refresh"));
                } else {
                    api.customers
                        .edit({
                            id: this.customer.id,
                            name: this.customer.name,
                            licenseNb: this.customer.licenseNb,
                            personalNb: this.customer.personalNb,
                            phoneNb: this.customer.phoneNb,
                            address: this.customer.address,
                        })
                        .then(() => this.$emit("refresh"));
                }
            },
            deleteCustomer(){
                api.customers.delete({
                    id: this.customer.id,
                })
                    .then(() => this.$emit("refresh"));
            },
        },
        computed: {
            isNew: function () {
                return !this.customer.id;
            },
        },
    }
</script>

<style scoped>

</style>