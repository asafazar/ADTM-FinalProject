/**
 * Created by OrlyGB on 27/05/2016.
 */
angular.module('MyApp')
    .controller('strategyKendo', function($scope, $http, $rootScope){
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
                    read: {
                        url: "/js/data/actions.json",
                        dataType: "json"
                    }
                },
                serverPaging: true,
                serverSorting: true,
                serverFiltering: true,
                pageSize: 5,
                filter: { field: "id", operator: "eq", value: dataItem.id },
                schema: {

                    data: function (data) {
                        return data.filter(function (strategy) {
                            return (strategy.id === dataItem.id);
                        });
                    },
                    total: function (data) {
                        return data['odata.count'];
                    },

                    model: {
                        id: "ActionID",
                        fields: {
                            StrategyID: { editable: false },
                            ActionID: { editable: false },
                            contractName: { nullable: false },
                            name: { nullable: false}
                        }
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
                { field: "contractName", title:"Contract Name", width: 100 },
                { field: "name", title:"Name", width: 100 },
                { command: ["edit", "destroy"], title: "&nbsp;", width: "200px" }
            ],
            editable: "popup"
        };
    };
});