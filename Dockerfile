FROM kbase/kbase:sdkbase.latest
MAINTAINER KBase Developer
# -----------------------------------------

# Insert apt-get instructions here to install
# any required dependencies for your module.

# RUN apt-get update
COPY ./deps/ /kb/deps
WORKDIR /kb/deps
RUN ./download_3rd_party_bins.sh

COPY ./lib/KBTree_cpp_lib/ /kb/deps/KBTree_cpp_lib
WORKDIR /kb/deps/KBTree_cpp_lib
RUN echo $KB_RUNTIME
RUN make all DEPLOY_RUNTIME=/kb/runtime
RUN cp /kb/deps/KBTree_cpp_lib/lib/perl_interface/TreeCppUtil.* /kb/deployment/lib/.

# -----------------------------------------

COPY ./ /kb/module
RUN mkdir -p /kb/module/work

WORKDIR /kb/module
RUN mkdir -p bin
RUN cp /kb/deps/bin/* ./bin/

RUN make

ENTRYPOINT [ "./scripts/entrypoint.sh" ]

CMD [ ]
