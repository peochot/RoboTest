FROM ubuntu:16.04
MAINTAINER phan.nguyen.huy@zalando.fi

COPY ./requirements.txt /home/requirements.txt

RUN  apt-get dist-upgrade -y \
    && apt-get update \
    && apt-get install -y --no-install-recommends \
          language-pack-en \
          curl \
          software-properties-common \
          lsb-release \
          python3 python3-dev python3-pip python3-setuptools \
          xvfb \
          firefox \
          wget  \
    && curl -sL https://deb.nodesource.com/setup_7.x | bash  \
    && apt-get install nodejs \
    && xargs -L 1 pip3 install < ./home/requirements.txt  \
    && apt-get clean -y \
    && apt-get autoremove -y \
    && rm -rf /tmp/* /var/tmp/* \
    && rm -rf /var/lib/apt/lists/* \
    && cd /home/ \
    && wget https://github.com/mozilla/geckodriver/releases/download/v0.13.0/geckodriver-v0.13.0-linux64.tar.gz \
    && tar -xvzf geckodriver-v0.13.0-linux64.tar.gz \
    && chmod +x geckodriver \
    && cp ./geckodriver /usr/local/bin/geckodriver \
    && mkdir /home/project

ENV LANG=en_US.UTF-8


ENTRYPOINT ["/bin/bash","/home/project/test.sh"]
