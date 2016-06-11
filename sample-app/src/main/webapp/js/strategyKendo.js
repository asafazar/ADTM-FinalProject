/**
 * Created by OrlyGB on 27/05/2016.
 */
angular.module('MyApp')
    .controller('strategyKendo', function($scope){
    $scope.strategyGridOptions = {
    dataSource: {
        type: "odata",
        transport: {
            read: {
                url: "/js/data/strategy.json",
                dataType: "json"
            },
            create: {
                url: '/OFB/PostMijnUrenOpAct',
                dataType: "json",
                type: "POST"
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
                id: "StrategyID",
                fields: {
                    StrategyID: { editable: false },
                    Description: { nullable: false },
                    Weekly: { type: "boolean", nullable: false},
                    Comment: { nullable: false },
                    Date: { nullable: false },
                    ExpirationDate: { nullable: false }
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
        { field: "StrategyID", title: "Strategy ID",  width: 120 },
        { field: "Description", title: "Description",  width: 140 },
        { field: "Weekly", title: "Weekly", width: 100 },
        { field: "Comment", title: "Comment",  width: 120 },
        { field: "Date", format: "{0: yyyy-MM-dd HH:mm:ss}" , title: "Date", width: 190 },
        { field: "ExpirationDate", title: "Expiration Date", width: 190, format: "{0: yyyy-MM-dd HH:mm:ss}" },
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
                    },
                },
                serverPaging: true,
                serverSorting: true,
                serverFiltering: true,
                pageSize: 5,
                filter: { field: "StrategyID", operator: "eq", value: dataItem.StrategyID },
                schema: {

                    data: function (data) {
                        return data.filter(function (strategy) {
                            return (strategy.StrategyID === dataItem.StrategyID);
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