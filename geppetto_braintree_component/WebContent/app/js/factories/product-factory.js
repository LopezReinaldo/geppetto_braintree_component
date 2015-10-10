app.factory('ProductData', function () {
    return {
        product_data: {},
        setData: function (data) {
            this.product_data = data;
        }
    };
});