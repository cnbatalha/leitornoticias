using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.IO;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using Microsoft.Phone.Controls;
using System.Xml.Serialization;
using System.Xml;
using System.Threading;
using Microsoft.Phone.Tasks;

namespace LeitorRSSC
{
    public partial class pageNoticias : PhoneApplicationPage
    {
        public rss RSS { get; set; }

        public StreamReader streamXml;

        public pageNoticias()
        {
            InitializeComponent();
        }

        private void LayoutRoot_Loaded(object sender, RoutedEventArgs e)
        {
            // Deserializando XAML
            //XmlSerializer serializer = new XmlSerializer(typeof(rss));
            PageTitle.Text = MainPage.Selecianda.titulo;

            HttpWebRequest httpRequest = (HttpWebRequest)WebRequest.CreateHttp(new Uri(MainPage.Selecianda.url));
            httpRequest.Method = "GET";

            //httpRequest.ContentType = "text/xml";

            httpRequest.BeginGetResponse(new AsyncCallback(ReadCallback), httpRequest);

            //XmlReader reader = XmlReader.Create("http://tecnologia.uol.com.br/ultnot/index.xml");
            //RSS = (rss)serializer.Deserialize(reader);

            //while (!carregou)
            //    cont++;

            //lbxNoticias.ItemsSource = RSS.channels.items;
        }

        private void ReadCallback(IAsyncResult asynchronousResult)
        {
            HttpWebRequest request = (HttpWebRequest)asynchronousResult.AsyncState;
            HttpWebResponse response = (HttpWebResponse)request.EndGetResponse(asynchronousResult);

            //streamXml = new StreamReader(response.GetResponseStream());

            using (StreamReader streamReader1 = new StreamReader(response.GetResponseStream(), System.Text.Encoding.UTF8 ))
            {
                //string resultString = streamReader1.ReadToEnd();
                // PageTitle.Text = "Using HttpWebRequest: " + resultString;
                XmlSerializer serializer = new XmlSerializer(typeof(rss));

                XmlReader reader = XmlReader.Create(streamReader1);

                RSS = (rss)serializer.Deserialize(reader);

                foreach (item i in RSS.channels.items)
                {

                    while (i.title.Contains("\t"))
                        i.title = i.title.Replace("\t", "");

                    while (i.title.Contains("\n"))
                        i.title = i.title.Replace("\n", "");

                }
                Dispatcher.BeginInvoke(() => { lbxNoticias.ItemsSource = RSS.channels.items; });
            }
        }

        private void TextBlock_Tap_1(object sender, GestureEventArgs e)
        {
            string url = (sender as TextBlock).Tag.ToString();
            url.Replace("\t", "");

            WebBrowserTask webbrowserTask = new WebBrowserTask();
            webbrowserTask.Uri = new Uri(url, UriKind.Absolute);
            webbrowserTask.Show();

        }
    }
}