apt-get update
apt-get install -y openjdk-6-jdk curl groovy unzip

echo "You have to login to vagrant at this point
vagrant ssh
curl -s get.gvmtool.net | bash
source "/home/vagrant/.gvm/bin/gvm-init.sh"
gvm install grails 2.4.4
cd /vagrant/Server
grails run-app"
