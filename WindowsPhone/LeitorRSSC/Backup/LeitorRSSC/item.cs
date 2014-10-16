using System;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using System.Collections.Generic;
using System.Xml.Serialization;

namespace LeitorRSSC
{

    public class Categoria
    { 
        public string titulo {get; set;}
        public string url { get; set; }
    }

    //[XmlRoot("rss")]
    public class rss
    {
        public string version { get; set; }
        [XmlElement("channel")]
        public channel channels { get; set; }
    }

    public class channel
    {
        public string title { get; set; }
        public string link { get; set; }
        public string description { get; set; }
        public string language { get; set; }
        public string category { get; set; }
        public string copyright { get; set; }
        //public image image { get; set; }
        [XmlElement("item")]
        public List<item> items { get; set; }
    }
    
    public class image
    {
        public string title { get; set; }
        public string url { get; set; }
        public string link { get; set; }
    }
    
    public class item
    {
        public string title { get; set; }
        public string link { get; set; }
        public string pubDate { get; set; }
        public string description { get; set; }
    }
    
}
