/**
 * Created by OrlyGB on 27/05/2016.
 */
angular.module('MyApp')
    .controller('strategyKendo', function($scope, $http, $rootScope, $interval){
    $scope.strategyGridOptions = {
    dataSource: {
        type: "odata",
        transport: {
            read: function(e) {
                    if ($rootScope.email) {
                        $http({
                            method: 'GET',
                            url: '/userStrategies',
                            params: {userid: $rootScope.email}
                        }).success(function (data, status, headers, config) {
                            e.success(data);
                        }).error(function (data, status, headers, config) {
                        });
                    }
            },
            create:
                function(e) {
                    if ($rootScope.email) {
                        $http({
                            method: 'GET',
                            url: '/AddStrategyServlet',
                            params: {name: e.data.name, isWeekly: e.data.isWeekly, comment: e.data.comment,
                                date: e.data.date, userId: $rootScope.email}
                        }).success(function (data, status, headers, config) {
                            e.success(data);
                        }).error(function (data, status, headers, config) {
                        });
                    }
                },
            destroy:
            function(e) {
                $http({
                    method: 'GET',
                    url: '/DeleteStrategyServlet',
                    params: {id: e.data.id}
                }).success(function (data, status, headers, config) {
                    e.success(data);
                }).error(function (data, status, headers, config) {
                });
            }
        },
        pageSize: 10,
        schema: {
            data: function (data) {
                return data;
            },
            total: function (data) {
                return data['odata.count'];
            },
            model: {
                id: "id",
                fields: {
                    id: { editable: false },
                    name: { nullable: false },
                    isWeekly: { type: "boolean", nullable: false},
                    comment: { nullable: false },
                    date: { nullable: false },
                    expirationDate: { editable: false, visible: false }
                }
            }
        },
        serverPaging: true,
        serverSorting: true
    },
    scrollable: false,
    batch: true,
    height: 550,
    filterable: true,
    groupable: true,
    sortable: true,
    pageable: true,
    columnMenu: true,
    /*dataBound: function() {
       this.expandRow(this.tbody.find("tr.k-master-row").first());
    },*/
    toolbar: ["create"],
    columns: [
        { field: "name", title: "Name",  width: 140 },
        { field: "isWeekly", title: "Weekly", width: 100 },
        { field: "comment", title: "Comment",  width: 120 },
        { field: "date", format: "{0: yyyy-MM-dd HH:mm:ss}" , title: "Date", width: 190 },
        { field: "expirationDate", title: "Expiration Date", width: 190, format: "{0: yyyy-MM-dd HH:mm:ss}" },
        { command: ["edit", "destroy"], title: "&nbsp;", width: "200px" }
    ],
    editable: "popup"
};

    $scope.actionsGridOptions = function(dataItem) {
        return {
            dataSource: {
                type: "odata",
                transport: {
                    read: function(e){
                        $http({
                            method: 'GET',
                            url: '/getPurchaseServlet',
                            params: {id: dataItem.id}
                        }).success(function (data, status, headers, config) {
                            refreshGrid();
                            e.success(data);
                        }).error(function (data, status, headers, config) {
                        });
                    },
                    update: function(e){
                        $http({
                            method: 'GET',
                            url: '/UpdatePurchaseServlet',
                            params: {id: e.data}
                        }).success(function (data, status, headers, config) {
                            e.success(data);
                        }).error(function (data, status, headers, config) {
                        });
                    }
                },
                serverPaging: true,
                serverSorting: true,
                serverFiltering: true,
                pageSize: 5,
                filter: { field: "strategyId", operator: "eq", value: dataItem.id },
                schema: {
                    data: function (data) {
                        return data;
                        },
                    total: function (data) {
                        return data['odata.count'];
                    },

                    model: {
                        fields: {
                            strikePrice: { editable: false },
                            pl: {editable:false},
                            ask1: { editable: false },
                            bid1: { editable: false },
                            buyNumber: { editable: true },
                            writeNumber: { editable: true },
                            buyPurchasePremium: { editable: false },
                            writePurchasePremium: { editable: false }
                        },
                        id: "id"
                    }
                }
            },
            scrollable: false,
            sortable: true,
            filterable: true,
            columnMenu: true,
            pageable: true,
            toolbar: ["create"],
            columns: [
                {field: "strikePrice", title:"Strike Price", width: "50px"},
                {field: "PL", title:"P&L", width: "50px"},
                {field: "buyPurchasePremium", title:"Buy Premium", width: "50px", value: function(e){
                    e.data.bid1 * e.data.buyNumber
                }},
                {field: "bid1", title:"Bid Price", width: "50px"},
                {field: "buyNumber", title:"Buy number", width: "50px"},
                {field: "writePurchasePremium", title:"Ask Premium", width: "50px", value: function(e){
                    e.data.ask1 * e.data.writeNumber
                }},
                {field: "ask1", title:"Ask Price", width: "50px"},
                {field: "writeNumber", title:"Ask number", width: "50px"},
                { command: ["edit"], title: "&nbsp;", width: "200px" }
            ],

            editable: "inline"
        };

    };

    function refreshGrid() {
        setInterval(function(){
            $('#actionsGridOptions').data('kendoGrid').dataSource.read();
        }, 5000)
    }

});