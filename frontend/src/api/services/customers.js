import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    allCustomers() {
        return HTTP.get(BASE_URL + "/customers", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    create(customer) {
        return HTTP.post(BASE_URL + "/customers", customer,{ headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    delete(customer){
        return HTTP.delete(BASE_URL + "/customers/" + customer.id, { headers: authHeader() }).then();
    },
    edit(customer) {
        return HTTP.patch(BASE_URL + "/customers", customer,{ headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
};