apt-get update
apt-get install -y openjdk-6-jdk groovy curl unzip

# copy the whole mounted grails app into /var/www
mkdir -p /var/www
cp -a /vagrant/Server/* /var/www/

echo "------------------------------------------------------------------------------------------

The VM is running, now you have to login to vagrant at this point
vagrant ssh

# then install grails:
curl -s http://get.gvmtool.net | bash
source "/home/vagrant/.gvm/bin/gvm-init.sh"
gvm install grails 2.4.4

# and run the app
cd /var/www/
grails run-app

The app will be accessible (when started) at
http://localhost:8090/Server/

"


# no ned to download https://gravysam@bitbucket.org/alphaAndOmega/energy_challenge.git