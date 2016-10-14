# swell

A service for projecting the health of a system onto something where
change is easily recognised: art.

## Usage

The environment variable `PICTURES_URL` will have to be set to a URL
containing a list of pictures that can be used.

    export PICTURES_URL=https://gist.githubusercontent.com/tgk/9562a094037361978c6382bd06d405e1/raw/3ec3872618685a5f1bcd6b07b9ef7b0fe5f91767/gistfile1.txt
    
We also need to configure a backend by setting `MONITOR_MODE` to either
`cloudwatch` or `http-endpoint`. The two modes work as follows:

- `http-endpoint` mode periodically requests a given HTTP endpoint and
  inspects the raw text. If the text changes, a different picture is
  viewed.
- `cloudwatch` collects all alarms from Cloudwatch and extracts alarm
  ARN, name, and current state. If any of this information changes a
  different picture is viewed.

For `http-endpoint` we also need to specify the endpoint:

    export MONITOR_MODE=http-endpoint
    export HTTP_ENDPOINT=http://localhost:1234/
    
For `cloudwatch` we also need to specify a region 

    export MONITOR_MODE=cloudwatch
    export CLOUDWATCH_AWS_REGION=eu-central-1

`lein-env` comes with default options which can get you started when in
development mode.

That server can be run using the lein plugin

    lein ring server 
    
by building a war and dropping it into a container 

    lein ring uberwar 
   
or by calling `(user/reset)` (in dev environments).

## License

Copyright © 2016 GoMore

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
