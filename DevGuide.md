1. DELETE Existing Index

curl -XDELETE localhost:9200/creditscore

2. Create New Index
curl -XPUT localhost:9200/creditscore

3. Create Index Type and Mapping

curl -H "Content-Type: application/json" -XPUT localhost:9200/creditscore/offer/_mappings -d '{
    "dynamic": "strict",
    "properties": {
                "bank": {"type" : "text"},
                "interest": {"type": "double"},
                "type":{"type":"text"},
                "limit":{"type":"double"},
                "productName":{"type":"text"},
                "yearlyFee":{"type":"double"},
                "rewards":{"type":"text"},
                "balanceTransfer":{"type":"text"}
        }
    }'

4. Add a document

curl -H "Content-Type: application/json" -XPOST localhost:9200/creditscore/offer -d '{
        "bank": "Bank of China",
        "interest": 10.14,
        "type": "Visa Card",
        "limit": 10000,
        "productName": "Holiday Offer",
        "yearlyFee": 1500,
        "rewards": "Macbook",
        "balanceTransfer": 1000
}'




curl -H "Content-Type: application/json" -XPOST localhost:9200/creditscore/offer -d '{
        "bank": "American Express",
        "interest": 20.74,
        "type": "Credit Card",
        "limit": 5000,
        "productName": " American Express Explorer® Credit Card",
        "yearlyFee": 395,
        "rewards": "400 Travel Credit ",
        "balanceTransfer": 2400
    }'

curl -H "Content-Type: application/json" -XPOST localhost:9200/creditscore/offer -d '{
        "bank": "Citi",
        "interest": 9.01,
        "type": "Credit Card",
        "limit": 6000,
        "productName": "New Year Offer",
        "yearlyFee": 1500,
        "rewards": "300 Travel credit",
        "balanceTransfer": 99
    }'

curl -H "Content-Type: application/json" -XPOST localhost:9200/creditscore/offer -d '{
        "bank": "Bank West",
        "interest": 13.09,
        "type": "Visa Card",
        "limit": 8000,
        "productName": "Christmas Offer",
        "yearlyFee": 1300,
        "rewards": "Beats Headphone",
        "balanceTransfer": 3000
    }'

curl -H "Content-Type: application/json" -XPOST localhost:9200/creditscore/offer -d '{
        "bank": "Coles",
        "interest": 5.14,
        "type": "credit Card",
        "limit": 7000,
        "productName": "Dashai Offer",
        "yearlyFee": 1250,
        "rewards": "LCD TV",
        "balanceTransfer": 1300
    }'

5. List documents

List all documents
curl localhost:9200/creditscore/offer/_search?q=*:*

curl -H"Content-Type: application/json" -XPOST localhost:9200/creditscore/offer/_search -d '{
    "query": {
        "match_all": {}
    }
}'

#show product name Christmas Offer
curl -H"Content-Type: application/json" -XPOST localhost:9200/creditscore/offer/_search -d '{
    "query": {
        "query_string": {
            "query": "productName:\"Christmas Offer\""
        }
    }
}'
    
curl localhost:9200/creditscore/offer/_search?q=productName:Christmas Offer

6. Query Filter
Query results are used to calculate score
Filter is cached and can make queries return faster

curl -H"Content-Type: application/json" -XPOST localhost:9200/creditscore/offer/_search -d '{
    "query": {
        "bool": {
            "must": {
                "match" : {
                    "productName": "Holiday Offer"
                }
            },
            "filter": [
                {
                    "match" : {
                        "type": "diner club"
                    }
                },
                {
                    "range": {
                        "limit": {
                            "gte": "0",
                            "lte": "10000"
                        }
                    }
                }

            ]
        }
    }
}'



7) example 

curl -H"Content-Type: application/json" -XPOST localhost:9200/creditscore/offer/_search -d '{
    "query": {
        "query_string": {
            "query": "bank:\"masubh\""
        }
    }
}'



curl -H"Content-Type:application/json" -XPOST "http://localhost:8090/v3/addCreditCard" -d '{"bank":"Macquarie Bank",
"interest":45.0,"type":"diner club",
"limit":17.0,"productName":"American Express",
"yearlyFee":2443.0,"rewards":"dOdluHJtjQ",
"balanceTransfer":8177.0
}'

--------


curl -H"Content-Type:application/json" -XPOST http://localhost:9200/creditscore/offer/_search -d '{
"query":{
    "match" : {"bank" : "ANZ"}
}
}'


curl -H"Content-Type:application/json" -XPOST http://localhost:9200/creditscore/offer/_search -d '{
    "query" : {
        "bool": {
            "should" :[ 
                {"match" : {"bank" : "uBank"}},
                {"match" : {"bank" : "ANZ"}}
            ],
            "must" : {
                "match" : {"productName" : "China Express"}
                }
                }
                }
}'

curl -H"Content-Type:application/json" -XPOST http://localhost:9200/creditscore/offer/_search -d '{
    "query": {
    "bool" : {
    "should" : [
     {"match" : {"bank" : "ANZ"}},
     {"match" : {"bank" : "Macquarie Bank"}}
    ]
}
}
}'





---------------------------

curl -H"Content-Type:application/json" -XPOST "http://localhost:8090/login" -d '{
    "username":"admin",
    "password":"admin"
}'

